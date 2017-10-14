package com.epam.lab.element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.
        DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * The method is called by the factory for each field in the class
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<IElement> decoratableClass = decoratableClass(field);
        // if the field class is decorated
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    /**
     * Returns the decorated field class,
     * or null if the class is not suitable for the decorator
     */
    @SuppressWarnings("unchecked")
    private Class<IElement> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            // must be given an annotation
            if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null) {
                return null;
            }

            // The list must be parameterized
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            // get a class for list items
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (IElement.class.isAssignableFrom(clazz)) {
            return (Class<IElement>) clazz;
        } else {
            return null;
        }
    }

    /**
     * Create an item.
     * Finds WebElement and passes it to a custom class
     */
    protected IElement createElement(ClassLoader loader, ElementLocator locator, Class<IElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    /**
     * Creating a list
     */
    @SuppressWarnings("unchecked")
    protected List<IElement> createList(ClassLoader loader, ElementLocator locator, Class<IElement> clazz) {

        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz);
        List<IElement> elements = (List<IElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return elements;
    }

}
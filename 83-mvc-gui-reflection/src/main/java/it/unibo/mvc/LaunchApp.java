package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.api.DrawNumberView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(String[] args)
                            throws ClassNotFoundException, 
                            NoSuchMethodException, 
                            InvocationTargetException,
                            InstantiationException, 
                            IllegalAccessException, 
                            IllegalArgumentException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        /*
        app.addView(new DrawNumberSwingView());
        //second GUI
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberStandardOutputView());
        */
        final Class<?> swingClass = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
        final Class<?> stdoutClass = Class.forName("it.unibo.mvc.view.DrawNumberStandardOutputView");
        //get the 0 ary construct
        final Constructor<?> swingConstructor = swingClass.getConstructor();
        final Constructor<?> stdoutConstructor = stdoutClass.getConstructor();
        for(int i = 0; i < 3; i++) {
           app.addView((DrawNumberView) swingConstructor.newInstance());
        app.addView((DrawNumberView) stdoutConstructor.newInstance());

        }
        


    }
}

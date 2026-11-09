package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawResult;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class DrawNumberStandardOutputView implements  DrawNumberView {

    private static final String APP_NAME = "Draw Number Console";
    private static final String NEW_GAME = ": a new game starts!";


   
    @Override
    public void setController(final DrawNumberController observer) {

    }
    @Override 
    public void start() {
        IO.println("Inizio partita...");
    }
    @Override
    public void result(final DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                IO.println(res.getDescription());
                return;
            }
            case YOU_WON -> IO.println(res.getDescription() + NEW_GAME);
            case YOU_LOST -> IO.println(res.getDescription() + NEW_GAME);
        }
    }


}
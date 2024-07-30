package com.abdullah.shojachat.logic;

/**
 * LogicEvents:
 *
 *
 */
public class AppEventManager extends Thread
{
    private static boolean running = true;
    //private ArrayList<EventListener> s;
    public static boolean isRunning() {
        return running;
    }

    public static void quit_EventManager() {
        AppEventManager.running = false;
    }

    @Override
    public void run()
    {
        while (AppEventManager.isRunning())
        {

        }
    }
}

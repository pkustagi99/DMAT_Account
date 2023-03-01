package com.amazon.dmataccountmanager;
/**
 * DMAT Account Manager
 *
 */
import com.amazon.dmataccountmanager.db.DB;
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to DMAT Account Manager Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        Menu menu = new Menu();
        
        if(args.length > 0) {
        	DB.FILEPATH = args[0];
        }
        menu.showMenu();
    }
}

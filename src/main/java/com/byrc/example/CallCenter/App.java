package com.byrc.example.CallCenter;

import com.byrc.example.model.Call;
import com.byrc.example.model.User;
import com.byrc.example.process.Dispatcher;

/**
 * Hello world!
 *
 */
public class App {
   public static void main(String[] args) {
      Dispatcher dispatcher= new Dispatcher();
      for (int i = 1; i <= 100; i++) {
         Call call = new Call(new User("Runnin_100_" + i));
         dispatcher.dispatchCall(call);
         
      }
   }
}

package com.byrc.example.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.byrc.example.model.Call;
import com.byrc.example.model.User;
import com.byrc.example.repository.CallRepository;

@RunWith(JUnit4.class)
public class DispatcherTest {

   private Dispatcher dispatcher = new Dispatcher();
   
   @Before
   public void setUp() {

      CallChecker.TIME_SLEEP_SECONDS = false;
      CallAssigner.resetTotal();

   }

   private void waitUntilQueueEmpty() {
      while (!CallRepository.getInstance().isQueueEmpty()) {
      }
   }

   @Test
   public void executeSigleCallTest() {
      CallAssigner.resetTotal();
      CallChecker.TIME_SLEEP_SECONDS = false;
      final Call call = new Call(new User("TEST"));
      dispatcher.dispatchCall(call);
      waitUntilQueueEmpty();
      assertTrue(CallRepository.getInstance().isQueueEmpty());

   }

   @Test
   public void execute10CallTest() {
      CallAssigner.resetTotal();
      CallChecker.TIME_SLEEP_SECONDS = false;
      for (int i = 1; i <= 10; i++) {
         Call call = new Call(new User("Test_" + i));
         dispatcher.dispatchCall(call);
      }

      waitUntilQueueEmpty();
      assertTrue(CallRepository.getInstance().isQueueEmpty());
      assertEquals(10, CallAssigner.getTotal());

   }

   @Test
   public void executeMoreThan10CallTest() {
      CallAssigner.resetTotal();
      CallChecker.TIME_SLEEP_SECONDS = false;
      for (int i = 1; i <= 20; i++) {
         Call call = new Call(new User("Test_" + i));
         dispatcher.dispatchCall(call);
      }

      waitUntilQueueEmpty();
      assertTrue(CallRepository.getInstance().isQueueEmpty());
      assertEquals(20, CallAssigner.getTotal());

   }

}

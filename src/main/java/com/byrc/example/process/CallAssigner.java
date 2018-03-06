package com.byrc.example.process;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.byrc.example.model.Call;
import com.byrc.example.model.Employee;
import com.byrc.example.repository.CallRepository;
import com.byrc.example.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallAssigner implements Runnable {

   private static boolean RUNNING = false;
   private ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
   private static int TOTAL_CALL_ASSIGNED = 0;

   public CallAssigner() {
      super();

   }

   @Override
  
   public void run() {

      setRunning(true);
      log.info("Starting thread for assign enqueue call");
      while (!CallRepository.getInstance().isQueueEmpty()) {
         log.info("Obtain free employee");
         Optional<Employee> employee = EmployeeRepository.getInstance().getFreeEmployee();
         if (employee.isPresent()) {
            log.info("Assigning employee to call");
            TOTAL_CALL_ASSIGNED++;
            final Call call = CallRepository.getInstance().getCall();
            call.setEmployee(employee.get());
            final CallChecker callChecker = new CallChecker(call);

            threadPoolExecutor.execute(callChecker);

         } else {
            log.info("No employees free to assign call");
         }

      }

     
      setRunning(false);
      log.info("End of thread for assign enqueue call");
   }

   public static void resetTotal() {
      TOTAL_CALL_ASSIGNED = 0;
   }

   public static int getTotal() {
      return TOTAL_CALL_ASSIGNED;
   }

   public static boolean isRunning() {
      return RUNNING;
   }

   public static void setRunning(boolean rUNNING) {
      RUNNING = rUNNING;
   }

}

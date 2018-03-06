package com.byrc.example.process;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.byrc.example.model.Call;
import com.byrc.example.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallChecker implements Runnable {

   public static boolean TIME_SLEEP_SECONDS = true;
   private Call call;

   public CallChecker(Call call) {
      super();
      this.call = call;
   }

   @Override
   public void run() {
      try {
         long startTime = System.currentTimeMillis();
         log.info("Starting call attend for employee assigned : {}", call.getEmployee().toString());
         Random random = new Random();
         call.setCallDuration(5 + random.nextInt(5));
         if (TIME_SLEEP_SECONDS) {
            TimeUnit.SECONDS.sleep(call.getCallDuration());
         } else {
            // just for testing prupose
            log.info("milliseconds duration {}",call.getCallDuration());
            TimeUnit.MILLISECONDS.sleep(call.getCallDuration());
         }
         EmployeeRepository.getInstance().addEmployee(call.getEmployee());
         long endTime = System.currentTimeMillis();
         log.info("End of call attend for employee assigned {} That took {} milliseconds",
               call.getEmployee().toString(), (endTime - startTime));
      } catch (InterruptedException e) {
         log.error(e.getMessage());
      }

   }

   public Call getCall() {
      return call;
   }

}

package com.byrc.example.process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.byrc.example.model.Call;
import com.byrc.example.repository.CallRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dispatcher {

   private ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(1);
   private CallAssigner callAssigner = new CallAssigner();

   public void dispatchCall(final Call call) {
      log.info("Start process to assigned call {} ", call.toString());
      CallRepository.getInstance().addCall(call);
      startAssigner();
      log.info("End of process to assigned call {} ", call.toString());

   }

   private void startAssigner() {
      log.info("Validating if it is necessary to re send a CallAssignor  ");
      if (!CallAssigner.isRunning()) {
         log.info("Sending CallAssignor  ");
         threadPoolExecutor.execute(callAssigner);
        
      } else {
         log.info("CallAssignor is running ");
      }
   }

}

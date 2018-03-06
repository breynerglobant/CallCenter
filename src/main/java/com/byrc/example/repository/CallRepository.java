package com.byrc.example.repository;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.byrc.example.model.Call;

public class CallRepository {

   private static CallRepository instance;

   private ConcurrentLinkedQueue<Call> callQueue = new ConcurrentLinkedQueue<Call>();

   private CallRepository() {
      callQueue.clear();

   }

   public static CallRepository getInstance() {
      if (instance == null) {
         instance = new CallRepository();
      }
      return instance;
   }

   public void addCall(Call call) {
      this.callQueue.add(call);
   }

   public Call getCall() {
      return this.callQueue.poll();
   }

   public boolean isQueueEmpty() {

      return this.callQueue.isEmpty();
   }

   public int getCantidadQueue() {
      return this.callQueue.size();
   }

   

}

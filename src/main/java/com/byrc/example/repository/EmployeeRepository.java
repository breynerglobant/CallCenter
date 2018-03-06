package com.byrc.example.repository;

import java.util.Optional;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.byrc.example.model.Director;
import com.byrc.example.model.Employee;
import com.byrc.example.model.Operator;
import com.byrc.example.model.Supervisor;

public class EmployeeRepository {
   private static EmployeeRepository instance;

   private ConcurrentLinkedQueue<Employee> operatorQueue = new ConcurrentLinkedQueue<Employee>();
   private ConcurrentLinkedQueue<Employee> supervisorQueue = new ConcurrentLinkedQueue<Employee>();
   private ConcurrentLinkedQueue<Employee> directorQueue = new ConcurrentLinkedQueue<Employee>();

   private EmployeeRepository() {
      this.loadEmployees();
   }

   public static EmployeeRepository getInstance() {
      if (instance == null) {
         instance = new EmployeeRepository();
      }
      return instance;
   }

   public void addEmployee(Employee employee) {
      if (employee instanceof Operator) {
         this.operatorQueue.add(employee);
      } else if (employee instanceof Supervisor) {
         this.supervisorQueue.add(employee);
      } else {
         this.directorQueue.add(employee);
      }
   }

   public int getTotalEmployees() {
      return operatorQueue.size() + supervisorQueue.size() + directorQueue.size();
   }

   public void clearQueues() {
      operatorQueue.clear();
      supervisorQueue.clear();
      directorQueue.clear();
   }

 
   public Optional<Employee> getFreeEmployee() {

      if (!this.operatorQueue.isEmpty())

         return Optional.of(this.operatorQueue.poll());
      else if (!this.supervisorQueue.isEmpty()) {
         return Optional.of(this.supervisorQueue.poll());
      } else if (!this.directorQueue.isEmpty()) {
         return Optional.of(this.directorQueue.poll());
      }
      return Optional.ofNullable(null);
   }

   private void loadEmployees() {

      this.clearQueues();
      for (int i = 1; i <= 4; i++) {
         this.addEmployee(new Operator());
      }

      for (int i = 1; i <= 3; i++) {
         this.addEmployee(new Supervisor());
      }

      for (int i = 1; i <= 3; i++) {
         this.addEmployee(new Director());
      }

   }

}

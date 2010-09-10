package com.chrismoos;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.*;

import java.util.*;

import org.wyki.zookeeper.cages.*;

public class App {
  public static void main( String[] args ) {
    
    try {      
      ZkSessionManager.initializeInstance("127.0.0.1:2181");

      while(true) {
        ZkWriteLock writeLock = new ZkWriteLock("/user/abc");
        long start = System.currentTimeMillis();
        System.out.println("acquiring lock...");
        
        writeLock.acquire();
        System.out.println(String.format("Retrieved lock in %d ms", System.currentTimeMillis() - start));
        System.out.println("got write lock...sleeping");
        
        Thread.sleep(500);
        writeLock.release();
        System.out.println("gave up lock");
      }
    }
    catch(Exception e) {
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
    }
  }
}

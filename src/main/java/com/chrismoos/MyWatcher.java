package com.chrismoos;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.*;

public class MyWatcher implements Watcher {
  public boolean connected;
  
  public MyWatcher() {
    connected = false;
  }
  
  public void process(org.apache.zookeeper.WatchedEvent event) {
    System.out.println("process: " + event.toString());
    
    if(event.getType() == Watcher.Event.EventType.None) {
      System.out.println("Connection state changed.");
      Watcher.Event.KeeperState state = event.getState();
      
      if(state == Watcher.Event.KeeperState.SyncConnected) {
        System.out.println("Connected to ZooKeeper.");
        
        connected = true;
      }
      else {
        System.out.println("Unknown state: " + state.toString());
      }
    }
  }
}
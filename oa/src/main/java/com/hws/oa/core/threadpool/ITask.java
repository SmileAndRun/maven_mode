package com.hws.oa.core.threadpool;

import com.hws.oa.model.LogModel;

public interface ITask {
  public void addLog(LogModel logModel);
}

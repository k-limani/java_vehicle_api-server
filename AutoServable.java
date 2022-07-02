package server;

import model.Automobile;

public interface AutoServable {

  // public void serve(int port);
  public void addAutoToStock(Automobile a1);

  public String getAllModels();
}

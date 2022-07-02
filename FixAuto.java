package adapter;

import exception.AutoException;

public interface FixAuto {

  // TODO figure out what this does
  public abstract String fix(int errnum, String errmsg) throws AutoException;
}

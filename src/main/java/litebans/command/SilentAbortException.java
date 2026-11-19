package litebans.command;

import litebans.config.Message;

public final class SilentAbortException extends AbortException {
   public SilentAbortException() {
      super(Message.HISTORY_ERROR_NO_LOGINRECORD);
   }
}

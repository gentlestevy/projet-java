package esieaa.jmusic.business;

import java.io.IOException;

/**
 * <p>base interface that represent each possible command<br/>
 * each implementation will redefine the executeInternal command (what the command do)
 * and the alias command (which alias triggers the command)
 * and the execute command (wrapped executeInternal with confirmation to avoid errors)
 * </p>
 */
public interface ICommand {
  /**
   * defines what to do when the command is triggered
   */
  void executeInternal() throws IOException;

  /**
   * attach an alias to the command
   * @return defined alias
   */
  String alias();

  /**
   * before executing a command confirm that user asked it with yes no question
   */
  void execute() throws IOException;
}

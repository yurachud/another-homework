package clone.swaper.infrastructure.command;

public interface Now {
    <C extends Command<R>, R extends Command.R> R execute(C command);
}
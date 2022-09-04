package command;

import misc.ResourceManager;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class CommandManager extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandManager.class);

    private Set<Command> commands;

    public CommandManager(){
        this.commands = new HashSet<>();
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public Optional<Command> getCommand(String name){
        return commands.stream().filter(cmd -> cmd.getName().equals(name)).findFirst();
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        LOGGER.info("{} is Ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentRaw());
        String[] msg = event.getMessage().getContentRaw().split(" ");
        String prefix = ResourceManager.getProperty("command.prefix");
        String[] params = new String[0];
        if(!msg[0].startsWith(prefix)){
            return;
        }
        if(msg.length > 1){
            params = Arrays.copyOfRange(msg,1,msg.length);
        }
        Iterator<Command> iterator = commands.iterator();
        Command command;
        while(iterator.hasNext()){
            command = iterator.next();
            if(command.getAliases().stream().anyMatch(alias -> msg[0].equalsIgnoreCase(prefix + alias))){
                command.handle(event, params);
                return;
            }
        }
        // Do nothing here if command wasn't found.
    }
}

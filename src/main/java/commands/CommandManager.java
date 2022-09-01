package commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import misc.ResourceManager;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


public class CommandManager extends ListenerAdapter {

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
        //TODO: some logging
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] msg = event.getMessage().getContentRaw().split(" ");
        String prefix = ResourceManager.getProperty("command.prefix");
        String[] args = null;
        if(!msg[0].startsWith(prefix)){
            return;
        }
        if(msg.length > 1){
            args = Arrays.copyOfRange(msg,1,msg.length);
        }
        Iterator<Command> iterator = commands.iterator();
        Command command;
        while(iterator.hasNext()){
            command = iterator.next();
            if(command.getAliases().stream().anyMatch(alias -> msg[0].equalsIgnoreCase(prefix + alias))){
                command.handle(event, args);
                return;
            }
        }
        // Do nothing here if command wasn't found.
    }
}

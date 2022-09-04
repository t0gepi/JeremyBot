package command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashSet;
import java.util.Set;

public abstract class Command{
    private String name;
    private Set<String> aliases;

    public Command(String name){
        this.aliases = new HashSet<>();
        this.name = name;
        this.addAlias(name);
    }

    public void addAlias(String alias){
        aliases.add(alias);
    }

    public Set<String> getAliases(){
        return aliases;
    }

    public String getName(){
        return name;
    }

    public abstract void handle(MessageReceivedEvent event, String... params);

}

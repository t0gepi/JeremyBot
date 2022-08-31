import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Commands extends ListenerAdapter {

     public void onMessageReceived(MessageReceivedEvent event){
         var args = event.getMessage().getContentRaw().split(" ");
         if(args[0].equals(Main.prefix + "info")){
             EmbedBuilder builder = new EmbedBuilder();
             builder.setTitle("Jeremy Fragrance");
             builder.setDescription("Businessman with 800k earnings per month\n" +
                                    "What's your favorite fragrance man?" );
             builder.setImage("https://cdn.discordapp.com/attachments/1013928183864762431/1014630557604397076/jeremy1.jpg");

             builder.setFooter("Created by Kalli");
             builder.setColor(0xf45642);


             event.getChannel().sendTyping().queue();
             event.getChannel().sendMessageEmbeds(builder.build()).queue();
         }
     }
}

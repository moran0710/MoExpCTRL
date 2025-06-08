package top.molab.minecraft.MoExpCtrl;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.*;

public class RuntimeDataManager {

    private static volatile RuntimeDataManager instance;
    private final List<Group> groups = new ArrayList<>();
    private FileConfiguration config;


    private RuntimeDataManager() {
    }

    public Group getPlayerGroup(Player player) {
        for (Group group : this.groups) {
            if (player.hasPermission(group.getPermission()) || !group.needPermission()) {
                return group;
            }
        }
        return null;
    }



    public static RuntimeDataManager getInstance() {
        if (instance == null) {
            synchronized (RuntimeDataManager.class) {
                if (instance == null) {
                    instance = new RuntimeDataManager();
                }
            }
        }
        return instance;
    }

    public void init(){
        this.groups.clear();
        MoExpCtrl.getInstance().reloadConfig();
        this.config = MoExpCtrl.getInstance().getConfig();
        Collection<String> groupKeys = ((MemorySection) this.config.get("groups")).getKeys(false);
        for (var group : groupKeys) {
            float exp = (float) this.config.getDouble("groups."+group+".exp");
            boolean needPermission = this.config.getBoolean("groups."+group+".need-permission");
            int priority =  this.config.getInt("groups."+group+".priority");
            this.groups.add(new Group(exp, group, needPermission, priority));
        }
        this.groups.sort(Comparator.comparingInt(Group::priority));

        MoExpCtrl.getInstance().getLogger().info(Group.getTableHead());
        for (var group : this.groups) {
            MoExpCtrl.getInstance().getLogger().info(group.toString());
            try{
                MoExpCtrl.getInstance().getServer().getPluginManager().addPermission(
                        new Permission(group.getPermission())
                );}
            catch (IllegalArgumentException ex){}
        }

    }

}

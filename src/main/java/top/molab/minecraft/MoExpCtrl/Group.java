package top.molab.minecraft.MoExpCtrl;

import org.jetbrains.annotations.NotNull;

public record Group(float exp, String name, boolean needPermission, int priority) {

    @Override
    public @NotNull String toString(){
        return "{ "+this.priority+" : "+this.name+" : "+this.needPermission+" : "+this.exp+" }";
    }

    public String getPermission(){
        return "moexpctrl."+this.name;
    }

    public static String getTableHead(){
        return "priority : name : need-permission : exp";
    }
}

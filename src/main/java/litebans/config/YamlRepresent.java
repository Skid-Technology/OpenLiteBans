package litebans.config;

import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.representer.Represent;

public class YamlRepresent implements Represent {
   public final YamlRepresenter yamlRepresenter;

   public YamlRepresent(YamlRepresenter var1) {
      this.yamlRepresenter = var1;
   }

   public Node representData(Object var1) {
      return this.yamlRepresenter.represent(((ConfigNode)var1).map);
   }
}

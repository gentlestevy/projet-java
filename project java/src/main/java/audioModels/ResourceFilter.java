package audioModels;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceFilter {


    public static List<Resource> getChansons(List<Resource> elements){

        return elements.stream().filter(e -> e.getType().equals(ResourceType.CHANSON))
                .collect(Collectors.toList());
    }
    public static List<Resource> getLivreAudios(List<Resource> elements){

        return elements.stream().filter(e -> e.getType().equals(ResourceType.LIVRE_AUDIO))
                .collect(Collectors.toList());
    }
}

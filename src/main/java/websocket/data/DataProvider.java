package websocket.data;

import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class DataProvider {

    private List<String> data = ImmutableList.of("Ciccio", "Pluto", "Pippo", "Clarabella");

    public String nextValue(){
        return data.get(new Random().nextInt(data.size()));
    }

}

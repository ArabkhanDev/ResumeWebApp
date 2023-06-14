import com.company.dao.impl.UserDaoImpl2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoImpl2IT {

    @Autowired
    UserDaoImpl2 userDao;

    @Test
    public void testGetAll(){

    }

}

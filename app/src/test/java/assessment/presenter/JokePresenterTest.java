package assessment.presenter;

import com.wiivv.assessment.presenter.main.JokePresenter;
import com.wiivv.assessment.view.panel.JokePanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class JokePresenterTest {

    private JokePresenter presenter;
    private JokePanel panel;

    @Before
    public void setUp() {
        presenter = mock(JokePresenter.class);
        panel = mock(JokePanel.class);
    }

    @After
    public void tearDown() {
        presenter = null;
        panel = null;
    }

    @Test
    public void setPanel() {
        doNothing().when(presenter).setPanel(any(JokePanel.class));
        presenter.setPanel(panel);
    }

    @Test
    public void requestSearch() {
        doNothing().when(presenter).searchJoke("hipster");
        presenter.searchJoke("hipster");
    }
}

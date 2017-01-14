package ch.bfh.psychologin.reporter.reporter.control;

import ch.bfh.psychologin.reporter.reporter.entity.Action;
import ch.bfh.psychologin.reporter.reporter.entity.Keystroke;
import ch.bfh.psychologin.reporter.reporter.entity.KeystrokeSession;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 14.01.2017.
 */

@Stateless
public class ActionTransformer {


    public List<KeystrokeSession> transformToKeystrokesPerSession(List<Action> actionList) {
        List<KeystrokeSession> keystrokesPerSession = new ArrayList<>();
        long lastSessionId = actionList.get(0).getSessionId();
        long firstMili = actionList.get(0).getTimestamp().getTime();
        long keyStrokeNumber = 1;
        KeystrokeSession keystrokeSession = new KeystrokeSession();
        keystrokeSession.setName(lastSessionId);
        for (Action action : actionList) {

            if(action.getSessionId() !=lastSessionId) {
                keystrokesPerSession.add(keystrokeSession);
                keystrokeSession = new KeystrokeSession();
                keyStrokeNumber = 1;
                firstMili = action.getTimestamp().getTime();
                lastSessionId = action.getSessionId();
                keystrokeSession.setName(lastSessionId);
            }
            Keystroke keystroke = new Keystroke();
            keystroke.setName(keyStrokeNumber++);
            keystroke.setValue(action.getTimestamp().getTime() - firstMili);
            keystrokeSession.getSeries().add(keystroke);
        }

        return keystrokesPerSession;
    }

}

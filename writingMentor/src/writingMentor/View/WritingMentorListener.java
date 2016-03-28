package writingMentor.View;

import java.util.EventListener;

public interface WritingMentorListener extends EventListener{
	public void motChanged(WritingMentorChangedEvent event);
}

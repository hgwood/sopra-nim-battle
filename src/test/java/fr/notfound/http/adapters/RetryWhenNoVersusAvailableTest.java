package fr.notfound.http.adapters;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.notfound.domain.ArenaException;
import fr.notfound.domain.Team;
import fr.notfound.http.adapters.RetryWhenNoVersusAvailable;
import fr.notfound.time.Delay;

public class RetryWhenNoVersusAvailableTest {
    
    @Rule public ExpectedException expectedException = ExpectedException.none();
    private final Team delegate = mock(Team.class);
    private final Delay retryDelay = mock(Delay.class);
    
    @Test public void retriesOnce() {
        when(delegate.currentVersus())
            .thenThrow(new ArenaException(""))
            .thenReturn(null);
        RetryWhenNoVersusAvailable sut = 
            new RetryWhenNoVersusAvailable(delegate, retryDelay, 2);
        sut.currentVersus();
    }
    
    @Test public void retriesTwice() {
        when(delegate.currentVersus())
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenReturn(null);
        RetryWhenNoVersusAvailable sut = 
            new RetryWhenNoVersusAvailable(delegate, retryDelay, 3);
        sut.currentVersus();
    }
    
    @Test public void retriesTenTimes() {
        when(delegate.currentVersus())
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenReturn(null);
        RetryWhenNoVersusAvailable sut = 
            new RetryWhenNoVersusAvailable(delegate, retryDelay, 11);
        sut.currentVersus();
    }
    
    @Test
    public void failsAfterAllAttemps() {
        expectedException.expect(ArenaException.class);
        expectedException.expectMessage(containsString("no game could be retrieved after"));
        when(delegate.currentVersus())
            .thenThrow(new ArenaException(""))
            .thenThrow(new ArenaException(""))
            .thenReturn(null);
        RetryWhenNoVersusAvailable sut = 
            new RetryWhenNoVersusAvailable(delegate, retryDelay, 1);
        sut.currentVersus();
    }
    
    @Test
    public void waitsForAsLongAsTheRetryDelayBeforeRetrying() {
        when(delegate.currentVersus())
            .thenThrow(new ArenaException(""))
            .thenReturn(null);
        RetryWhenNoVersusAvailable sut = 
            new RetryWhenNoVersusAvailable(delegate, retryDelay, 2);
        sut.currentVersus();
        verify(retryDelay).trigger();
    }

}

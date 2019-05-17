package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  @BeforeEach
  public void init(){
    primaryTorpedoStore = mock(TorpedoStore.class);
    secondaryTorpedoStore = mock(TorpedoStore.class);

    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.fire(any(int.class))).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(primaryTorpedoStore, times(1)).fire(any(int.class));

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
      when(primaryTorpedoStore.fire(any(int.class))).thenReturn(true);
      when(secondaryTorpedoStore.fire(any(int.class))).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
      verify(primaryTorpedoStore, times(1)).fire(any(int.class));
      verify(secondaryTorpedoStore, times(1)).fire(any(int.class));

    // Assert
    assertEquals(true, result);
  }

}

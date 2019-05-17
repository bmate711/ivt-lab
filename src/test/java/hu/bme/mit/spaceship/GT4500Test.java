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

  @Test
  public void fireTorpedo_firstTimePrimaryStoreFired_Succses(){
      when(primaryTorpedoStore.fire(any(int.class))).thenReturn(true);
      when(secondaryTorpedoStore.fire(any(int.class))).thenReturn(true);
      ship.fireTorpedo(FiringMode.SINGLE);
      verify(primaryTorpedoStore, times(1)).fire(any(int.class));
      verify(secondaryTorpedoStore, times(0)).fire(any(int.class));
  }

    @Test
    public void fireTorpedo_firedAlternatingStoreFired_Succses(){
        when(primaryTorpedoStore.fire(any(int.class))).thenReturn(true);
        when(secondaryTorpedoStore.fire(any(int.class))).thenReturn(true);
        ship.fireTorpedo(FiringMode.SINGLE);
        ship.fireTorpedo(FiringMode.SINGLE);
        ship.fireTorpedo(FiringMode.SINGLE);
        verify(primaryTorpedoStore, times(2)).fire(any(int.class));
        verify(secondaryTorpedoStore, times(1)).fire(any(int.class));
    }

    @Test
    public void fireTorpedo_primaryStoreEmptySecondPrimaryStoreFired_Succses(){
        when(primaryTorpedoStore.fire(any(int.class))).thenReturn(true);
        when(secondaryTorpedoStore.fire(any(int.class))).thenReturn(true);
        when(primaryTorpedoStore.isEmpty()).thenReturn(true);
        ship.fireTorpedo(FiringMode.SINGLE);
        verify(primaryTorpedoStore, times(0)).fire(any(int.class));
        verify(secondaryTorpedoStore, times(1)).fire(any(int.class));
  }

  @Test
  public void fireTorpedo_fireStoreFaliournotTryFireTheOtherone_Succses(){
      when(primaryTorpedoStore.fire(any(int.class))).thenReturn(false);
      when(secondaryTorpedoStore.fire(any(int.class))).thenReturn(true);
      boolean result = ship.fireTorpedo(FiringMode.SINGLE);
      verify(primaryTorpedoStore, times(1)).fire(any(int.class));
      verify(secondaryTorpedoStore, times(0)).fire(any(int.class));
      assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_fireAllPrimaryEmpty_Succses(){
      when(primaryTorpedoStore.fire(any(int.class))).thenReturn(true);
      when(secondaryTorpedoStore.fire(any(int.class))).thenReturn(true);
      when(primaryTorpedoStore.isEmpty()).thenReturn(true);
      ship.fireTorpedo(FiringMode.ALL);
      verify(primaryTorpedoStore, times(0)).fire(any(int.class));
      verify(secondaryTorpedoStore, times(1)).fire(any(int.class));
  }

}

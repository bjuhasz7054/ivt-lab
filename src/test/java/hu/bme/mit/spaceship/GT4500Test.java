package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryMock = mock(TorpedoStore.class);
  private TorpedoStore secondaryMock = mock(TorpedoStore.class);
  @BeforeEach
  public void init(){
   
    this.ship = new GT4500(primaryMock, secondaryMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  
  @Test
  public void fireTorpedo_PrimaryEmpty_Success(){
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(false);
    when(secondaryMock.fire(1)).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    assertEquals( true, result);
  }

  @Test
  public void fireTorpedo_BothEmpty_Failure(){
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    assertEquals( false, result);
  }

  @Test
  public void fireTorpedo_FirstShotLast_Success()
  {
    when(primaryMock.isEmpty()).thenReturn(false);
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(false);
    when(secondaryMock.fire(1)).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    assertEquals( true, result);
  }

  @Test
  public void fireTorpedo_SecondaryShotLast_Success()
  {
    when(primaryMock.isEmpty()).thenReturn(false);
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(false);
    when(secondaryMock.fire(1)).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    assertEquals( true, result);
  }
  @Test
  public void fireTorpedo_Fire11_Failure()
  {
    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(false);
    when(secondaryMock.fire(11)).thenReturn(false);
   
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    assertEquals( false, result);
  }
  @Test
  public void fireTorpedo_ShootAllWithOneEmpty_Success()
  {
    when(primaryMock.isEmpty()).thenReturn(false);
    when(primaryMock.fire(1)).thenReturn(true);
    when(secondaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.fire(1)).thenReturn(false);
   
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    assertEquals( true, result);
  }
  @Test
  public void fireTorpedo_ShootPrimaryWhenSecondaryEmpty_Success()
  {
    when(primaryMock.isEmpty()).thenReturn(false);
    when(primaryMock.fire(1)).thenReturn(true);
   
    when(secondaryMock.fire(1)).thenReturn(true);
    ship.fireTorpedo(FiringMode.SINGLE);
    when(secondaryMock.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    assertEquals( true, result);
  }
}

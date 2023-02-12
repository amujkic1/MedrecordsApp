package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.RecordsDaoImpl;
import ba.unsa.etf.rpr.domain.Records;
import ba.unsa.etf.rpr.exceptions.MyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RecordManagerTest {

    private RecordManager recordManager;
    private Records record;
    private RecordsDaoImpl recordsDaoMock;
    private List<Records> list;

    @BeforeEach
    public void initialzeObjectsWeNeed(){
        recordManager = Mockito.mock(RecordManager.class);
        record = new Records();
        record.setPatient_id(1);
        record.setId(78);

        recordsDaoMock = Mockito.mock(RecordsDaoImpl.class);
        list = new ArrayList<>();
        list.addAll(Arrays.asList(new Records(1), new Records(2), new Records(3),
                new Records(4), new Records(5)));
    }

    @Test
    void add() throws MyException {

        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::recordsDao).thenReturn(recordsDaoMock);

        when(DaoFactory.recordsDao().getAll()).thenReturn(list);
        Mockito.doCallRealMethod().when(recordManager).add(record);
        MyException myException = Assertions.assertThrows(MyException.class, () -> {
            recordManager.add(record);}, "Can't add record with ID. ID is autogenerated");

        assertEquals("Can't add record with ID. ID is autogenerated", myException.getMessage());
        daoFactoryMockedStatic.verify(DaoFactory::recordsDao);
        Mockito.verify(recordManager).add(record);
        daoFactoryMockedStatic.close();
    }

    @Test
    void findUserRecord(){
        String username = "JohnDoe";
        RecordManager recordManager = new RecordManager();

        NullPointerException nullPointerException = Assertions.assertThrows(NullPointerException.class, ()->{
            recordManager.findUserRecord(username);}, "Patient does not exist in the database");
        Assertions.assertEquals("Patient does not exist in the database", nullPointerException.getMessage());

    }


}











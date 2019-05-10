package de.fhws.fiw.pvs.assignment_5.zikzak.database.inmemory;

import de.fhws.fiw.pvs.assignment_5.sutton.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.pvs.assignment_5.zikzak.database.MessageDao;
import de.fhws.fiw.pvs.assignment_5.zikzak.models.MessageModel;

public class MessageInMemoryStorage extends AbstractInMemoryStorage<MessageModel> implements MessageDao {
}

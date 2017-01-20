package com.csc.fsg.life.dao.model;

import java.sql.Connection;
import java.util.ArrayList;

import com.csc.fsg.life.dao.dataaccessor.DataAccessor;

/**
   This class would serve as the holder class for all
   Models of a given type
**/
public class DAOModelCollection<E extends DAOModel> extends ArrayList<E> {
	private String modelName;

	private DAOModelCollection<E> copy = null;

	/**
	   Create a DAOModelCollection object. 
	   @param modelName the type of models objects in the collection.
	**/
	public DAOModelCollection(String modelName) {
		super();

		this.modelName = modelName;
	}

	/**
	   Clear all items in the collection.
	**/
	public void clear() {
		copy = new DAOModelCollection<E>(modelName);
		copy.addAll(0, this);
		super.clear();
	}

	/**
	   Simple clear .. calls super class in list.
	**/
	private void justClear() {
		super.clear();
	}

	/**
	   Returns the copy of the collection.
	**/
	public DAOModelCollection<E> getCopy() {
		return copy;
	}
	
	/**
	   Returns the model name. 
	**/
	public String getModelName() {
		return modelName;
	}

	/**
	   Change all objects in the collection with the specified state, 
	   to the specified model state.
	**/
	private void markAllObjectToState(int fromstate, int tostate) {
		int size = copy.size();
		for (int i = 0; i < size; i++) {
			E model = copy.get(i);
			if (model.getModelState() == fromstate) {
				model.setModelState(tostate);
			}
		}
	}

	/**
	   Find the specified model in the collection that equals() the model
	   parameter and return the index in the collection or -1 if not found.
	**/
	private int getMatch(DAOModelCollection<E> coll, DAOModel model) {
		int size = coll.size();

		for (int i = 0; i < size; i++) {
			DAOModel temp = coll.get(i);
			if (temp.equals(model)) {
				return i;
			}
		}
		return -1;
	}

	/**
	   Find the specified model in the collection that equals() the model
	   parameter and return the index in the collection or -1 if not found.
	**/
	public int match(DAOModel model) {
		return getMatch(this, model);
	}

	/**
	   Find the specified model in the collection that equals() the model
	   parameter and return true if found and false if not.
	**/
	public boolean contains(DAOModel model) {
		if (getMatch(this, model) > -1) {
			return true;
		}
		return false;
	}

	public String toString() {
		DAOModel model = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.size(); i++) {
			model = this.get(i);
			sb.append(model.toString() +"\n");
		}
		return sb.toString();
	}

	/**
	   Iterates over the list of models in this collection and 
	   determines the sub-list of models that need to update the DB table.
	   That means any model that is new or changed from the one in the DB.
	**/
	public void createChangedList() 
	{
		DAOModelCollection<E> copy = getCopy();

		if (copy != null) {
			markAllObjectToState(DAOModel.READ_FROM_DB,
								 DAOModel.READ_FROM_DB_DELETED);
			int size = size();
			for (int i = 0; i < size; i++) {
				E model = (E)get(i);
				if (copy.size() == 0) {
					if (model.getModelState() != DAOModel.CREATED_IN_MEMORY) {
						model.setModelState(DAOModel.CREATED_IN_MEMORY);
					}
					copy.add(model);
				} else {
					int match = getMatch(copy, model);
					if (match == -1) {
						if (model.getModelState()!= DAOModel.CREATED_IN_MEMORY) {
							model.setModelState(DAOModel.CREATED_IN_MEMORY);
						}
						copy.add(model);
					} else {
						DAOModel temp = copy.get(match);
						temp.setModelState(DAOModel.READ_FROM_DB);
					}
				}
			}

			super.clear();
			super.addAll(0, copy);
			copy.justClear();
			copy = null;
		}
	}

	/**
	   Loop over collection and call handleOneModel() for each model 
	   in the collection.
	**/
	public int handleList(Connection con) throws DAOModelException {
		int sumResult = 0;
		int size = size();

		createChangedList();

		if (size == 0) {
			return 0;
		} else {
			DataAccessor dao = ((DAOModel) get(0)).getDataAccessorObject();
			for (int i = 0; i < size; i++) {
				DAOModel model = (DAOModel) get(i);
				int oneResult = handleOneModel(con, dao, model);
				sumResult = sumResult + oneResult;
			}
		}
		return sumResult;
	}

	/**
	   Iterates over the list and handles each model by 
	   calling handleOneModel().  handleOneModel() basically does an 
	   insert, update, or delete for each model depending upon what is needed.
	   @param con The database connection to use.
	   @throws DAOModelException if there is an exception during processing.
	**/
	public int handleTableList(Connection con) throws DAOModelException {
		int sumResult = 0;
		int size = size();

		if (size == 0) {
			return 0;
		} else {
			DataAccessor dao = ((DAOModel) get(0)).getDataAccessorObject();
			for (int i = 0; i < size; i++) {
				DAOModel model = (DAOModel) get(i);
				int oneResult = handleOneModel(con, dao, model);
				sumResult = sumResult + oneResult;
			}
		}
		return sumResult;
	}

	private int handleOneModel(
		Connection con,
		DataAccessor dao,
		DAOModel model)
		throws DAOModelException {
		int result = 0;

		if (model.getModelState() == DAOModel.CREATED_IN_MEMORY) {
			result = insert(con, dao, model);
		} else {
			if (model.getModelState() == DAOModel.READ_FROM_DB_DELETED) {
				result = delete(con, dao, model);
			} else {
				if (model.getModelState() == DAOModel.READ_FROM_DB_UPDATED) {
					result = update(con, dao, model);
				} else {
					if (model.getModelState() == DAOModel.READ_FROM_DB) {
						if (model.hasChanged()) {
							if (model.hasKeysChanged()) {
								DAOModel oldmodel = model.getOldCopy();

								if (oldmodel != null) {
									result = delete(con, dao, oldmodel);
								} else {
									throw new DAOModelException(
										"Model "
											+ model
											+ " has state READ_FROM_DB but no dbCopy object");
								}
								result = insert(con, dao, model);
							} else {
								result = update(con, dao, model);
							}
						}
					}
				}
			}
		}
		return result;
	}

	private int insert(Connection con, DataAccessor dao, DAOModel model)
		throws DAOModelException {
		int result = 0;
		try {
			result = dao.insert(con, model);
		} catch (Exception e) {
			// Convert all exceptions to DAOModel exception.
			throw new DAOModelException(e);
		}
		return result;
	}

	private int delete(Connection con, DataAccessor dao, DAOModel model)
		throws DAOModelException {
		int result = 0;
		try {
			result = dao.delete(con, model);
		} catch (Exception e) {
			throw new DAOModelException(e);
		}
		return result;
	}

	private int update(Connection con, DataAccessor dao, DAOModel model)
		throws DAOModelException {
		int result = 0;
		try {
			result = dao.update(con, model);
		} catch (Exception e) {
			throw new DAOModelException(e);
		}
		return result;
	}

}

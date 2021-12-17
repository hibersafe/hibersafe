package com.ufrn.dtos;

public enum ExceptionsEnum {

	AnnotationException, AssertionFailure, CallbackException, DuplicateMappingException,
	HibernateError, HibernateException, InstantiationException, InvalidMappingException, JDBCException, LazyInitializationException,
	MappingException, MappingNotFoundException, NonUniqueObjectException, NonUniqueResultException, ObjectDeletedException,
	ObjectNotFoundException, OptimisticLockException, PersistentObjectException, PessimisticLockException,
	PropertyAccessException, PropertyNotFoundException, PropertySetterAccessException, PropertyValueException, QueryException,
	QueryParameterException, QueryTimeoutException, ResourceClosedException, SessionException, StaleObjectStateException,
	StaleStateException, TransactionException, TransientObjectException, TransientPropertyValueException, TypeMismatchException,
	UnknownEntityTypeException, UnknownProfileException, UnresolvableObjectException, UnsupportedLockAttemptException,
	WrongClassException, ConstraintViolationException, DataException, GenericJDBCException,
	JDBCConnectionException, LockAcquisitionException, LockTimeoutException, SQLGrammarException;
	
}

import { useState } from 'react';
import Results from '../components/Results';
import styles from './Home.module.scss';
import axios from 'axios';

interface GoogleAPI {
  data: {
    organic_results: [{link: string}];
  };
}

interface HibersafeAPI {
  data: {
    topSimilarity: [
      {url: string;}
    ];
  };
}

export default function Home(){
  const exceptions = ["AnnotationException", "AssertionFailure", "CallbackException", "DuplicateMappingException"
    ,"HibernateError", "HibernateException", "InstantiationException", "InvalidMappingException", "JDBCException", "LazyInitializationException"
    ,"MappingException", "MappingNotFoundException", "NonUniqueObjectException", "NonUniqueResultException", "ObjectDeletedException"
    ,"ObjectNotFoundException", "OptimisticLockException", "PersistentObjectException", "PessimisticLockException"
    ,"PropertyAccessException", "PropertyNotFoundException", "PropertySetterAccessException", "PropertyValueException", "QueryException"
    ,"QueryParameterException", "QueryTimeoutException", "ResourceClosedException", "SessionException", "StaleObjectStateException"
    ,"StaleStateException", "TransactionException", "TransientObjectException", "TransientPropertyValueException", "TypeMismatchException" 
    ,"UnknownEntityTypeException", "UnknownProfileException", "UnresolvableObjectException", "UnsupportedLockAttemptException" 
    ,"WrongClassException", "ConstraintViolationException", "DataException", "GenericJDBCException", "JDBCConnectionException" 
    ,"LockAcquisitionException", "LockTimeoutException", "SQLGrammarException"];
  const [exception, setException] = useState<string>(exceptions[0]);
  const [resultsA, setResultsA] = useState<string[]>([]);
  const [resultsB, setResultsB] = useState<string[]>([]);
  const [stacktrace, setStacktrace] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const smallest = () => {
    return resultsA.length < resultsB.length ? resultsA.length : resultsB.length;
  }

  const calculateResults = async () => {
    setResultsA([]);
    setResultsB([]);
    if (!stacktrace){
      alert('Informe uma stacktrace para continuar!');
    }else{
      setLoading(true);
      try{
        let returnInfoA = await axios.get<any, GoogleAPI>(`https://api.scaleserp.com/search?api_key=10689D5EECF24286A6D38792B3366D3A&q=site:stackoverflow.com ${stacktrace}&flatten_results=true`);
        let returnInfoB = await axios.post<any, HibersafeAPI>(`http://localhost:8080/api/question/exceptionEnum/${exception}`, {stacktrace});

        const value = Math.floor(Math.random() * (100 - 1)) + 1;
        if (value % 2 === 0){
          returnInfoA.data.organic_results.forEach(async or => {
            setResultsA(arr => [...arr, or.link]);
          });

          returnInfoB.data.topSimilarity.forEach(async ts => {
            setResultsB(arr => [...arr, ts.url]);
          });
        }else{
          returnInfoA.data.organic_results.forEach(async or => {
            setResultsB(arr => [...arr, or.link]);
          });

          returnInfoB.data.topSimilarity.forEach(async ts => {
            setResultsA(arr => [...arr, ts.url]);
          });
        }

        setLoading(false);
      }catch(e){
        alert('Ocorreu algum erro. Por favor, tente novamente.');
        setResultsA([]);
        setResultsB([]);
        setLoading(false);
        console.log(e);
      }
    }
  }

  return (
    <div className={styles.pageRoot}>
      <h1>Hibersafe</h1>
      <div className={styles.inputGroup}>
        <label>Selecione a exceção lançada e informe a stacktrace:</label>
        <select onChange={e => setException(e.target.value)}>
          {exceptions.map(exception => <option>{exception}</option>)}
        </select>
        <textarea id="stacktrace" onChange={e => setStacktrace(e.target.value)} maxLength={1000}/>
        <button onClick={calculateResults} disabled={!stacktrace}>
          Buscar
        </button>
      </div>
      {loading && 
      <img alt='Carregando...' height='100em' width='118em%' src='loading.gif'/>}
      {resultsA.length > 0 && !loading &&
        <div className={styles.results}>
          <div className={styles.sideA}>
            <h2>Lado A</h2>
            <div className={styles.resultList}>
              {
                resultsA.map((r, index) => index < smallest() && 
                  <Results link={r} index={index} side={'A'} key={index}/>
                )
              }
            </div>
          </div>
          <span className={styles.divider}/>
          <div className={styles.sideB}>
          <h2>Lado B</h2>
            <div className={styles.resultList}>
            {
                resultsB.map((r, index) => index < smallest() && 
                  <Results link={r} index={index} side={'B'}  key={index}/>
                )
              }
            </div>
          </div>
        </div>
      }
    </div>
  )
}
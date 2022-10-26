import { lazy, Suspense } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

const Home = lazy(() => import('pages/Home'));
const NotFound = lazy(() => import('pages/NotFound'));

export default function AppRouter() {
  return (
    <main className='container'>
      <Router>
        <Suspense fallback={<p>Carregando...</p>}>
          <Routes>
            <Route path='/' element={<Home />}>
            </Route>
            <Route path='*' element={<NotFound />} />
          </Routes>
        </Suspense>
      </Router>
    </main>
  );
}
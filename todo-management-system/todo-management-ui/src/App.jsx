import './App.css'
import ListTodoComponent from './components/ListTodoComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
function App() {
  
  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
          <Routes>
            <Route path='/' element={<ListTodoComponent />}></Route>
            <Route path='/todos' element={<ListTodoComponent />}></Route>
          </Routes>  
        <FooterComponent />
      </BrowserRouter> 
    </>
  )
}

export default App

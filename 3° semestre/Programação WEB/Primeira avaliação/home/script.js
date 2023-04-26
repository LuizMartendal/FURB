function logout() {
    localStorage.removeItem('login')
    this.isLogged()
}

function isLogged() {
    const login = document.getElementById('login-page')
    const logout = document.getElementById('logout-page')
    const user = document.getElementById('user')
    
    if (localStorage.getItem('login') != null) {
        let image = document.createElement('img')
        image.src = '../imgs/logged.png'
        image.style.height = '6vh'
        image.style.backgroundColor = 'white'
        image.style.borderRadius = '1vw'
        image.style.padding = '1vh'
        image.style.marginLeft = '1vw'

        let a = document.createElement('a')
        a.href = '../cadastrar/index.html'
        a.style.textDecoration = 'none'
        a.appendChild(image)

        user.innerHTML = localStorage.getItem('login')
        user.appendChild(a)

        login.hidden = true
        logout.hidden = false
    } else {
        user.innerHTML = 'Usuário não autenticado'

        login.hidden = false
        logout.hidden = true
    }
}


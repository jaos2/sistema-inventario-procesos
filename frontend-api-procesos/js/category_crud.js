//funciones para operaciones crud
const urlApiCategory = 'http://localhost:8088/category'; //colocar la url con el puerto
const headersCategory = {
  Accept: 'application/json',
  'Content-Type': 'application/json',
  Authorization: `Bearer ${localStorage.token}`,
};

function listar() {
  validaToken();
  let settings = {
    method: 'GET',
    headers: headersCategory,
  };
  fetch(urlApiCategory, settings)
    .then((response) => {
      if (!response.ok) {
        throw new Error('fail network');
      }
      return response.json();
    })
    .then(function (categories) {
      let categorias = '';
      for (const categoria of categories) {
        categorias += `
                <tr>
                    <th scope="row">${categoria.category_id}</th>
                    <td class="text-center">${categoria.nameCategory}</td>
                    <td class="text-center">${categoria.description}</td>
                    <td class="text-center">${categoria.status}</td>
                    <td>
                    <a href="#" onclick="verModificarCategoria('${categoria.category_id}')" class="btn btn-outline-warning">
                    <i class="fa-solid fa-pen-to-square"></i>
                    </a>
                    <a href="#" onclick="verCategoria('${categoria.category_id}')" class="btn btn-outline-info">
                        <i class="fa-solid fa-eye"></i>
                    </a>
                    <a href="#" onclick="eliminaCategoria('${categoria.category_id}')" class="btn btn-outline-danger">
                        <i class="fa-solid fa-trash"></i>
                    </a>
                    </td>
                </tr>`;
      }
      document.getElementById('listar').innerHTML = categorias;
    })
    .catch((error) => {
      if (error.message === 'fail network') {
        let listarElement = document.getElementById('listar');
        listarElement.innerHTML =
          '<p id="vacio">https://www.youtube.com/watch?v=nPAp-gT5gPI&ab_channel=FacultadAutodidacta</p>';
        let vacioElement = document.querySelector('#vacio');
        vacioElement.classList.add('w-100', 'fs-4', 'mt-5');
      } else {
        console.error(
          'https://www.youtube.com/watch?v=nPAp-gT5gPI&ab_channel=FacultadAutodidacta: ',
          error
        );
      }
    });
}

function verModificarCategoria(id) {
  validaToken();
  let settings = {
    method: 'GET',
    headers: headersCategory,
  };
  fetch(urlApiCategory + '/' + id, settings)
    .then((categoria) => categoria.json())
    .then(function (categoria) {
      let cadena = '';
      if (categoria) {
        cadena = `
                <div class="p-3 mb-2 bg-light text-dark">
                    <h1 class="display-5"> Modificar Categoría</h1>
                </div>
              
                <form action="" method="post" id="modificar">
                    <input type="hidden" name="id" id="id" value="${categoria.category_id}">
                    <label for="nameCategory" class="form-label">Nombre categoria</label>
                    <input type="text" class="form-control" name="nameCategory" id="nameCategory" required value="${categoria.nameCategory}"> <br>
                    <label for="description"  class="form-label">Descripción</label>
                    <input type="text" class="form-control" name="description" id="description" required value="${categoria.description}"> <br>
                    <label for="status"  class="form-label">Estado</label>
                    <input type="text" class="form-control" name="status" id="status" required value="${categoria.status}"> <br>
                    <button type="button" class="btn btn-outline-warning" 
                        onclick="modificarCategoria('${categoria.category_id}')">Modificar
                    </button>
                </form>`;
      }
      document.getElementById('contentModal').innerHTML = cadena;
      let myModal = new bootstrap.Modal(
        document.getElementById('modalCategoria')
      );
      myModal.toggle();
    });
}

async function modificarCategoria(id) {
  validaToken();
  let myForm = document.getElementById('modificar');
  let formData = new FormData(myForm);
  let jsonData = {};
  for (let [k, v] of formData) {
    //convertimos los datos a json
    jsonData[k] = v;
  }
  const request = await fetch(urlApiCategory + '/' + id, {
    method: 'PUT',
    headers: headersCategory,
    body: JSON.stringify(jsonData),
  });
  if (request.ok) {
    alertas('Category created', 1);
    listar();
  } else {
    const data = await request.json(); // Espera a que la promesa se resuelva
    console.log(data); // Aquí puedes manejar la data de la respuesta
    const dataArray = Object.values(data);
    console.log(dataArray); // Aquí puedes manejar la data de la respuesta
    let dataResponse = '';
    for (let v of dataArray) {
      dataResponse += '<li>' + v + '</li>';
    }

    alertas('Error: <br>' + dataResponse, 2);
  }
  document.getElementById('contentModal').innerHTML = '';
  let myModalEl = document.getElementById('modalCategoria');
  let modal = bootstrap.Modal.getInstance(myModalEl); // Returns a Bootstrap modal instance
  modal.hide();
}

function verCategoria(id) {
  validaToken();
  let settings = {
    method: 'GET',
    headers: headersCategory,
  };
  fetch(urlApiCategory + '/' + id, settings)
    .then((categoria) => categoria.json())
    .then(function (categoria) {
      let cadena = '';
      if (categoria) {
        cadena = `
                <div class="p-3 mb-2 bg-light text-dark">
                    <h1 class="display-5"> Visualizar Categoría</h1>
                </div>
                <ul class="list-group">
                    <li class="list-group-item">Categoría: ${categoria.nameCategory}</li>
                    <li class="list-group-item">Descripción: ${categoria.description}</li>
                    <li class="list-group-item">Estado: ${categoria.status}</li>
    
                </ul>`;
      }
      document.getElementById('contentModal').innerHTML = cadena;
      let myModal = new bootstrap.Modal(
        document.getElementById('modalCategoria')
      );
      myModal.toggle();
    });
}

async function createCategory() {
  let myForm = document.getElementById('registerForm');
  let formData = new FormData(myForm);
  let jsonData = {};
  for (let [k, v] of formData) {
    //convertimos los datos a json
    jsonData[k] = v;
  }
  const request = await fetch(urlApiCategory, {
    method: 'POST',
    headers: headersCategory,
    body: JSON.stringify(jsonData),
  });
  if (request.ok) {
    alertas('Category created', 1);
    listar();
  } else {
    const data = await request.json(); // Espera a que la promesa se resuelva
    console.log(data); // Aquí puedes manejar la data de la respuesta
    const dataArray = Object.values(data);
    console.log(dataArray); // Aquí puedes manejar la data de la respuesta
    let dataResponse = '';
    for (let v of dataArray) {
      dataResponse += '<li>' + v + '</li>';
    }

    alertas('Error: <br>' + dataResponse, 2);
  }
  document.getElementById('contentModal').innerHTML = '';
  let myModalEl = document.getElementById('modalCategoria');
  let modal = bootstrap.Modal.getInstance(myModalEl); // Returns a Bootstrap modal instance
  modal.hide();
}

function createCategoryForm() {
  cadena = `       
            <form action="" method="post" id="registerForm">
                <input type="hidden" name="id" id="id">
                <label for="nameCategory" class="form-label">Nombre categoria</label>
                <input type="text" class="form-control" name="nameCategory" id="nameCategory" required> <br>
                <label for="description"  class="form-label">Descripción</label>
                <input type="text" class="form-control" name="description" id="description" required> <br>
                <label for="status"  class="form-label">Estado</label>
                <input type="text" class="form-control" name="status" id="status" required> <br>
                <button type="button" class="btn btn-outline-info" onclick="createCategory()">Registrar</button>
            </form>`;
  document.getElementById('contentModal').innerHTML = cadena;
  let myModal = new bootstrap.Modal(document.getElementById('modalCategoria'));
  myModal.toggle();
}

let idToDelete = null;
let deleteModal = null;

function eliminaCategoria(id) {
  idToDelete = id;
  deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
  deleteModal.show();
}

document.getElementById('confirmDelete').addEventListener('click', function () {
  if (idToDelete) {
    validaToken();
    let settings = {
      method: 'DELETE',
      headers: headersCategory,
    };
    fetch(urlApiCategory + '/' + idToDelete, settings)
      .then((response) => {
        if (response.status === 204) return {};
        else return response.json();
      })
      .then(function (data) {
        listar();
        alertas('The category has been deleted successfully!', 1);
      });
    idToDelete = null;
    deleteModal.hide();
  }
});

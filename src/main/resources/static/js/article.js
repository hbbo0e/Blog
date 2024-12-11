// 삭제
const deleteButton = document.getElementById('delete-btn');

if (deleteButton){
  deleteButton.addEventListener('click', event => {
    let id = document.getElementById('article-id').value;
    fetch(`/api/article/${id}`, {
      method: 'DELETE'
    })
    .then(() => {
      alert('completed delete');
      location.replace('/articles');
    });
  });
}

// 수정
const modifyButton = document.getElementById('modify-btn');

if(modifyButton){
  modifyButton.addEventListener('click', event => {
    let params = new URLSearchParams(location.search);
    let id = params.get('id');

    fetch(`/api/article/${id}`, {
      method: 'PUT',
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        title: document.getElementById('title').value,
        content: document.getElementById('content').value
      })
    })
    .then(() => {
      alert('completed modify');
      location.replace(`/article/${id}`);
    });
  });
}

// 등록
const createButton = document.getElementById("create-btn")

if(createButton){
  createButton.addEventListener("click", (event) => {
    fetch("/api/articles", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        title: document.getElementById("title").value,
        content: document.getElementById("content").value,
      }),
    }).then(() => {
      alert("completed create");
      location.replace("/articles");
    });
  });
}
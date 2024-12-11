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
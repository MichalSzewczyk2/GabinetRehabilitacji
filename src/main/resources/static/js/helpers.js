function showAlert(message) {
    if (message != null) {
        alert(message);
    }
}

function deleteAlert(id, path) {
    if (confirm('Are you sure you want to delete')) {
        fetch(path + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'text/html'
            }
        })
            .then(response => response.json())
    }
    location.reload();
}
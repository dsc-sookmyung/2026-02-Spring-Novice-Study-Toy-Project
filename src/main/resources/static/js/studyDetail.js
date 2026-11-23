const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', async () => {
        const csrfToken = document.querySelector('meta[name="_csrf"]').content;
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;


        fetch(deleteButton.dataset.deleteUrl, {
            method: 'DELETE',
            headers: { [csrfHeader]: csrfToken }
        })
            .then(response => {
                if (response.status !== 204) {
                    alert('삭제에 실패했습니다.');
                    return;
                }

                alert('삭제가 완료되었습니다.');
                location.replace(deleteButton.dataset.listUrl);
            })
            .catch(() => {
                alert('서버에 연결하지 못했습니다.');
            })
    });
}
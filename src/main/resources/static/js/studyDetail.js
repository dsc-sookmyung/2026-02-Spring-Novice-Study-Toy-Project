const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', async () => {
        const csrfToken = document.querySelector('meta[name="_csrf"]').content;
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

        deleteButton.disabled = true;

        try {
            const response = await fetch(deleteButton.dataset.deleteUrl, {
                method: 'DELETE',
                headers: { [csrfHeader]: csrfToken }
            });

            if (response.status === 204) {
                window.location.replace(deleteButton.dataset.listUrl);
            } else if (response.status === 404) {
                window.alert('스터디를 찾을 수 없습니다.');
            } else if (response.status === 403) {
                window.alert('삭제 권한이 없거나 요청을 확인할 수 없습니다.');
            } else {
                window.alert('스터디를 삭제하지 못했습니다.');
            }
        } catch {
            window.alert('서버에 연결하지 못했습니다.');
        } finally {
            deleteButton.disabled = false;
        }
    });
}
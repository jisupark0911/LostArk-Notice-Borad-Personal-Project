function toggleCollectiblePoints(type) {
        const pointsElement = document.getElementById(`points-${type}`);
        const buttonElement = document.querySelector(`button[data-type="${type}"]`);

        if (!pointsElement || !buttonElement) {
            console.log(`type을 찾을 수 없습니다.: ${type}`);
            return;
        }


        const allButtons = document.querySelectorAll('.toggle-points-btn');
        allButtons.forEach(function(button) {
            if (button !== buttonElement) {
                button.textContent = '보기';
            }
        });

        if (type !== '모코코 씨앗') {
            const collectiblePoints = document.querySelectorAll('.collectible-content');
            collectiblePoints.forEach(function(content) {
                const pointElement = content.querySelector('.point');
                const point = parseInt(pointElement.textContent.trim(), 10);

                const checkStatus = content.querySelector('.check-status');
                if (point === 1) {
                    checkStatus.classList.add('checked');
                } else if (point === 0) {
                    checkStatus.classList.remove('checked');
                }
                checkStatus.style.display = 'block';
            });
        } else {
            const collectiblePoints = document.querySelectorAll('.collectible-content');
            collectiblePoints.forEach(function(content) {
                const checkStatus = content.querySelector('.check-status');
                checkStatus.classList.remove('checked');
                checkStatus.style.display = 'none';
            });
        }

        const allPointsElements = document.querySelectorAll('.collectible-points');
        allPointsElements.forEach(function(el) {
            if (el !== pointsElement) {
                el.style.display = 'none';
            }
        });

        if (pointsElement.style.display === 'none') {
            pointsElement.style.display = 'block';
            buttonElement.textContent = '접기';
        } else {
            pointsElement.style.display = 'none';
            buttonElement.textContent = '보기';
        }
    }

    document.addEventListener("DOMContentLoaded", function() {
        const toggleButtons = document.querySelectorAll('.toggle-points-btn');

        toggleButtons.forEach(button => {
            const type = button.getAttribute('data-type');
            button.addEventListener('click', () => toggleCollectiblePoints(type));
        });
    });
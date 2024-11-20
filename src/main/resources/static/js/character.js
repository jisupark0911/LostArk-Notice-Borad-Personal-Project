document.addEventListener('DOMContentLoaded', function() {
            //장비 배경 색상
            var equipments = document.querySelectorAll('.characterEquipments');
            equipments.forEach(function(equipment) {
                var gradeElement = equipment.querySelector('.equipmentGrade');
                var armoryImage = equipment.querySelector('.armory-image');
                var nameElement = equipment.querySelector('.equipmentName');

                if (gradeElement && armoryImage && nameElement) {
                    var equipmentGrade = gradeElement.textContent.trim();

                    if (equipmentGrade === '고대') {
                        armoryImage.style.backgroundColor = '#dcc999';
                        nameElement.style.color = 'rgb(217, 171, 72)';
                    } else if (equipmentGrade === '유물') {
                        armoryImage.style.backgroundColor = '#a24006';
                        nameElement.style.color = '#a24006';
                    } else if (equipmentGrade === '전설') {
                        armoryImage.style.backgroundColor = '#a86200';
                        nameElement.style.color = '#a86200';
                    } else if (equipmentGrade === '영웅') {
                        armoryImage.style.backgroundColor = '#480d5d';
                        nameElement.style.color = '#480d5d';
                    } else if (equipmentGrade === '희귀') {
                        armoryImage.style.backgroundColor = '#113d5d';
                        nameElement.style.color = '#113d5d';
                    } else if (equipmentGrade === '고급') {
                        armoryImage.style.backgroundColor = '#304911';
                        nameElement.style.color = '#304911';
                    } else if (equipmentGrade === '에스더') {
                        armoryImage.style.backgroundColor = '#2faba8';
                        nameElement.style.color = '#2faba8';
                    } else {
                        armoryImage.style.backgroundColor = '#ffffff';
                    }
                }
            });
            //보석 배경 색상
            var gemContainers = document.querySelectorAll('.gem-container');
            gemContainers.forEach(function(gemContainer) {
                var gemElements = gemContainer.querySelectorAll('.gem-image');
                var gemGradeElements = gemContainer.querySelectorAll('.gemGrade');

                gemElements.forEach(function(gemImage, index) {
                    var gemGradeElement = gemGradeElements[index];

                    if (gemGradeElement) {
                        var gemGrade = gemGradeElement.textContent.trim().toLowerCase();

                        console.log(gemGrade);

                        if (gemGrade === '고대') {
                            gemImage.style.backgroundColor = '#dcc999';
                        } else if (gemGrade === '유물') {
                            gemImage.style.backgroundColor = '#a24006';
                        } else if (gemGrade === '전설') {
                            gemImage.style.backgroundColor = '#a86200';
                        } else if (gemGrade === '영웅') {
                            gemImage.style.backgroundColor = '#480d5d';
                        } else if (gemGrade === '희귀') {
                            gemImage.style.backgroundColor = '#113d5d';
                        } else if (gemGrade === '고급') {
                            gemImage.style.backgroundColor = '#304911';
                        } else {
                            gemImage.style.backgroundColor = '#ffffff';
                        }
                    }
                });
            });
            // 아바타 색상
            var avatarContainers = document.querySelectorAll('.avatar-container');
            avatarContainers.forEach(function(avatarContainer) {
                var leftAvatars = avatarContainer.querySelectorAll('.leftAvatars .avatar-main');
                var rightAvatars = avatarContainer.querySelectorAll('.rightAvatars .avatar-main');

                leftAvatars.forEach(function(avatar) {
                    var avatarGradeElement = avatar.querySelector('.avatarGrade');
                    var avatarNameElement = avatar.querySelector('.avatarName');

                    if (avatarGradeElement && avatarNameElement) {
                        var avatarGrade = avatarGradeElement.textContent.trim().toLowerCase();

                        if (avatarGrade === '고대') {
                            avatarNameElement.style.color = '#dcc999';
                        } else if (avatarGrade === '유물') {
                            avatarNameElement.style.color = '#a24006';
                        } else if (avatarGrade === '전설') {
                            avatarNameElement.style.color = '#a86200';
                        } else if (avatarGrade === '영웅') {
                            avatarNameElement.style.color = '#480d5d';
                        } else if (avatarGrade === '희귀') {
                            avatarNameElement.style.color = '#113d5d';
                        } else if (avatarGrade === '고급') {
                            avatarNameElement.style.color = '#304911';
                        } else {
                            avatarNameElement.style.color = '#ffffff';
                        }
                    }
                });

                rightAvatars.forEach(function(avatar) {
                    var avatarGradeElement = avatar.querySelector('.avatarGrade');
                    var avatarNameElement = avatar.querySelector('.avatarName');

                    if (avatarGradeElement && avatarNameElement) {
                        var avatarGrade = avatarGradeElement.textContent.trim().toLowerCase();

                        if (avatarGrade === '고대') {
                            avatarNameElement.style.color = '#dcc999';
                        } else if (avatarGrade === '유물') {
                            avatarNameElement.style.color = '#a24006';
                        } else if (avatarGrade === '전설') {
                            avatarNameElement.style.color = '#a86200';
                        } else if (avatarGrade === '영웅') {
                            avatarNameElement.style.color = '#480d5d';
                        } else if (avatarGrade === '희귀') {
                            avatarNameElement.style.color = '#113d5d';
                        } else if (avatarGrade === '고급') {
                            avatarNameElement.style.color = '#304911';
                        } else {
                            avatarNameElement.style.color = '#ffffff';
                        }
                    }
                });
            });

            //안쓰는 스킬, 레벨 1인스킬 삭제
            const skillLevels = document.querySelectorAll('.skill-level');

            skillLevels.forEach(skillLevel => {
                const levelText = skillLevel.textContent || skillLevel.innerText;
                const level = parseInt(levelText.replace('Level: ', '').trim());

                if (!isNaN(level) && level === 1) {
                    const skillMainElement = skillLevel.closest('.skill-container');
                    if (skillMainElement) {
                        skillMainElement.remove();
                    } else {
                        console.error("No .skill-main found for this .skill-level element.");
                    }
                }
            });

            //수집 포인트 프로그레스바 계산
            document.querySelectorAll('.progress-bar').forEach(function(progressBar) {
                const point = parseInt(progressBar.getAttribute('data-point'));
                const maxPoint = parseInt(progressBar.getAttribute('data-maxpoint'));

                const progressWidth = (point / maxPoint) * 100;

                console.log("point: " + point);
                console.log("maxPoint: " + maxPoint);
                console.log("progressWidth: " + progressWidth + "%");

                progressBar.style.width = progressWidth + '%';
            });

        });
    const abilityTab = document.querySelector('.tab-ability');
    const skillTab = document.querySelector('.tab-skill');
    const avatarTab = document.querySelector('.tab-avatar');
    const collectPointTab = document.querySelector('.tab-collectPoint');

    const abilityButton = document.getElementById('menu-ability');
    const skillButton = document.getElementById('menu-skill');
    const avatarButton = document.getElementById('menu-avatar');
    const collectPointButton = document.getElementById('menu-collectPoint');

    abilityTab.style.display = 'block';
    skillTab.style.display = 'none';
    avatarTab.style.display = 'none';
    collectPointTab.style.display = 'none';

    abilityButton.addEventListener('click', () => {
        abilityTab.style.display = 'block';
        skillTab.style.display = 'none';
        avatarTab.style.display = 'none';
        collectPointTab.style.display = 'none';
    });

    skillButton.addEventListener('click', () => {
        abilityTab.style.display = 'none';
        skillTab.style.display = 'block';
        avatarTab.style.display = 'none';
        collectPointTab.style.display = 'none';
    });

    avatarButton.addEventListener('click', () => {
        abilityTab.style.display = 'none';
        skillTab.style.display = 'none';
        avatarTab.style.display = 'block';
        collectPointTab.style.display = 'none';
    });

    collectPointButton.addEventListener('click', () => {
        abilityTab.style.display = 'none';
        skillTab.style.display = 'none';
        avatarTab.style.display = 'none';
        collectPointTab.style.display = 'block';
    });

    const filteredSkills = skills.filter(skill => skill.level >= 4);
    const template = document.getElementById('template').innerHTML;
    const rendered = Mustache.render(template, { characterCombatSkills: filteredSkills });
    document.getElementById('output').innerHTML = rendered;



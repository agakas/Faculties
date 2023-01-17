from rest_framework import fields, serializers
from .models import Faculty, Department

class FacultySerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Faculty
        fields = ('id', 'faculty_name')

class DepartmentSerializer(serializers.HyperlinkedModelSerializer):
    faculty = serializers.CharField(source='faculty.id')
    class Meta:
        model = Department
        fields = ('id', 'department_name', 'year', 'auditorium', 'boss', 'phone', 'email', 'employee_count', 'bachelors_count', 'master_count', 'faculty')
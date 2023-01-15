from rest_framework import viewsets

from .serializer import FacultySerializer, DepartmentSerializer
from .models import Faculty, Department

# Create your views here.
class FacultyViewSet(viewsets.ModelViewSet):
    queryset = Faculty.objects.all().order_by('id')
    serializer_class = FacultySerializer

class DepartmentViewSet(viewsets.ModelViewSet):
    queryset = Department.objects.all().order_by('id')
    serializer_class = DepartmentSerializer